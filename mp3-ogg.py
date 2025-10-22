import os
import subprocess
import sys
import tkinter as tk
from tkinter import filedialog, messagebox

def convert_to_ogg():
    file_paths = filedialog.askopenfilenames(
        title="Select MP3 or WAV files",
        filetypes=[("Audio Files", "*.mp3 *.wav")]
    )

    if not file_paths:
        return

    # Detect entity files and group them by (entity_name, sound_type)
    entity_groups = {}
    non_entity = []
    for path in file_paths:
        parts = os.path.normpath(path).split(os.sep)
        if "entity" in parts:
            idx = parts.index("entity")
            # Check that we have at least two more segments: name and type
            if idx + 2 < len(parts):
                name = parts[idx + 1]
                sound_type = parts[idx + 2]
                if sound_type in {"ambient", "death", "hurt"}:
                    key = (name, sound_type)
                    entity_groups.setdefault(key, []).append(path)
                    continue
        non_entity.append(path)

    # Prepare startupinfo to hide console window on Windows
    startupinfo = None
    creationflags = 0
    if sys.platform.startswith('win'):
        startupinfo = subprocess.STARTUPINFO()
        startupinfo.dwFlags |= subprocess.STARTF_USESHOWWINDOW
        creationflags = subprocess.CREATE_NO_WINDOW

    successes = []
    failures = []

    # Process entity files with custom naming
    for (name, sound_type), paths in entity_groups.items():
        count = len(paths)
        for i, file_path in enumerate(paths, start=1):
            # build output filename
            if count > 1:
                base = f"{name}_{sound_type}_{i}"
            else:
                base = f"{name}_{sound_type}"
            output_dir = os.path.dirname(file_path)
            output_path = os.path.join(output_dir, base + ".ogg")

            try:
                subprocess.run(
                    ["ffmpeg", "-i", file_path, output_path],
                    check=True,
                    stdout=subprocess.DEVNULL,
                    stderr=subprocess.DEVNULL,
                    startupinfo=startupinfo,
                    creationflags=creationflags
                )
                os.remove(file_path)
                successes.append((file_path, output_path))
            except subprocess.CalledProcessError:
                failures.append((file_path, "Conversion failed"))
            except OSError as e:
                failures.append((file_path, f"Converted but failed to delete original: {e}"))

    # Process non-entity files normally
    for file_path in non_entity:
        output_path = os.path.splitext(file_path)[0] + ".ogg"
        try:
            subprocess.run(
                ["ffmpeg", "-i", file_path, output_path],
                check=True,
                stdout=subprocess.DEVNULL,
                stderr=subprocess.DEVNULL,
                startupinfo=startupinfo,
                creationflags=creationflags
            )
            os.remove(file_path)
            successes.append((file_path, output_path))
        except subprocess.CalledProcessError:
            failures.append((file_path, "Conversion failed"))
        except OSError as e:
            failures.append((file_path, f"Converted but failed to delete original: {e}"))

    # Build summary message
    msg_lines = []
    if successes:
        msg_lines.append("Succeeded:")
        for src, dst in successes:
            msg_lines.append(f"  {os.path.basename(src)} → {os.path.basename(dst)}")
    if failures:
        msg_lines.append("")
        msg_lines.append("Failed:")
        for src, reason in failures:
            msg_lines.append(f"  {os.path.basename(src)}: {reason}")

    msg = "\n".join(msg_lines)
    if failures:
        messagebox.showwarning("Conversion Results", msg)
    else:
        messagebox.showinfo("Conversion Results", msg)

# GUI setup
root = tk.Tk()
root.title("FFmpeg Audio Converter")
root.geometry("400x200")

label = tk.Label(root, text="Convert MP3/WAV to OGG", font=("Arial", 12))
label.pack(pady=15)

button = tk.Button(root, text="Select Files", command=convert_to_ogg)
button.pack()

root.mainloop()
