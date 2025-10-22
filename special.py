import os
import tkinter as tk
from tkinter import filedialog, messagebox

def clean_file(filepath):
    """Replaces all non-ASCII characters in a file with '?'."""
    try:
        with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()
        cleaned = ''.join(ch if ord(ch) < 128 else '?' for ch in content)
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(cleaned)
        return True
    except Exception as e:
        print(f"Error processing {filepath}: {e}")
        return False

def clean_java_files(folder):
    count = 0
    for root, _, files in os.walk(folder):
        for file in files:
            if file.endswith('.java'):
                filepath = os.path.join(root, file)
                if clean_file(filepath):
                    count += 1
    return count

def select_folder():
    folder = filedialog.askdirectory(title="Select folder containing .java files")
    if not folder:
        return
    result = clean_java_files(folder)
    messagebox.showinfo("Done", f"Cleaned {result} .java files in:\n{folder}")

# GUI setup
root = tk.Tk()
root.title("Java Cleaner – Nonstandard Character Fixer")
root.geometry("400x200")
root.resizable(False, False)

label = tk.Label(root, text="Select a folder to clean all .java files:", font=("Arial", 11))
label.pack(pady=30)

btn = tk.Button(root, text="Browse Folder", command=select_folder, font=("Arial", 10), width=20, height=2)
btn.pack()

root.mainloop()
