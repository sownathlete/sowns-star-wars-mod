import os
from PIL import Image, ImageTk
import tkinter as tk
from tkinter import filedialog

def replace_magenta_with_liquid_palette_support(container_path, liquid_img, output_name):
    container_img = Image.open(container_path)
    
    # Convert both images to RGBA to ensure they're in the same mode
    container_img = container_img.convert('RGBA')
    liquid_img = liquid_img.convert('RGBA')
    
    container_pixels = container_img.load()
    liquid_pixels = liquid_img.load()

    MAGENTA = (255, 0, 255, 255)  # Including alpha channel

    for y in range(container_img.size[1]):
        for x in range(container_img.size[0]):
            if container_pixels[x, y] == MAGENTA:
                r, g, b, a = liquid_pixels[x, y]
                container_pixels[x, y] = (r, g, b, 255)  # Set alpha to fully opaque

    container_img.save(output_name)

class DrinkTextureApp:
    def __init__(self, master):
        self.master = master
        master.title("Drink Texture Modifier")

        # Upload button
        self.upload_button = tk.Button(master, text="Upload Liquid Texture", command=self.upload_liquid_texture)
        self.upload_button.pack(pady=10)

        # Label to display detected containers
        self.detected_containers_label = tk.Label(master, text="Detected Containers:")
        self.detected_containers_label.pack(pady=10)
        
        # Container for displaying list of containers with checkboxes
        self.container_frame = tk.Frame(master)
        self.container_frame.pack(pady=10)
        
        # Dictionary to keep track of checkboxes and their state
        self.checkboxes = {}

        # Generate button
        self.generate_button = tk.Button(master, text="Generate", command=self.generate_textures, state=tk.DISABLED)
        self.generate_button.pack(pady=10)

    def upload_liquid_texture(self):
        filepath = filedialog.askopenfilename(filetypes=[("PNG files", "*.png")])
        if filepath:
            self.liquid_path = filepath
            self.list_detected_containers()
            self.generate_button.config(state=tk.NORMAL)

    def list_detected_containers(self):
        # Clear previous checkboxes
        for widget in self.container_frame.winfo_children():
            widget.destroy()

        # Detect containers
        self.detected_containers = [f for f in os.listdir('.') if f.startswith('drink_') and f.endswith('.png')]

        for container in self.detected_containers:
            container_var = tk.BooleanVar(value=True)
            chk = tk.Checkbutton(self.container_frame, text=container, variable=container_var)
            chk.pack(anchor='w')
            self.checkboxes[container] = container_var

    def generate_textures(self):
        # Only process selected containers
        selected_containers = [container for container, var in self.checkboxes.items() if var.get()]
        
        for container in selected_containers:
            output_name = f"{os.path.basename(self.liquid_path).split('_')[0]}{container.split('_')[1].replace('.png', '').capitalize()}.png"
            replace_magenta_with_liquid_palette_support(container, Image.open(self.liquid_path), output_name)

# Create the GUI
root = tk.Tk()
app = DrinkTextureApp(root)
root.mainloop()

# Add this at the very end of the script to prevent the window from closing immediately when run by double-clicking
input("Press Enter to close...")
