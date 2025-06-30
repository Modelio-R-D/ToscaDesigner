import os
import re

def correct_java_imports(generated_code_dir):
    """
    Corrects specific import statements in Java files within a given directory.

    Args:
        generated_code_dir (str): The path to the directory containing
                                  your generated Java code.
    """
    # Define the replacements
    replacements = {
        "import com.modeliosoft.modelio.api.module.mda.MdaProxyException;":
            "import fr.softeam.toscadesigner.impl.MdaProxyException;",
        "import com.modeliosoft.modelio.api.module.mda.IMdaProxy;":
            "import fr.softeam.toscadesigner.impl.IMdaProxy;"
    }

    print(f"Starting import correction in: {generated_code_dir}")
    corrected_files_count = 0

    # Walk through the directory to find all Java files
    for root, _, files in os.walk(generated_code_dir):
        for file_name in files:
            if file_name.endswith(".java"):
                file_path = os.path.join(root, file_name)
                
                # Read the file content
                with open(file_path, 'r', encoding='utf-8') as f:
                    content = f.read()

                original_content = content # Keep original to check if changes were made

                # Perform replacements
                for old_import, new_import in replacements.items():
                    content = content.replace(old_import, new_import)

                # If content changed, write it back to the file
                if content != original_content:
                    with open(file_path, 'w', encoding='utf-8') as f:
                        f.write(content)
                    print(f"  Corrected: {file_path}")
                    corrected_files_count += 1

    print(f"Import correction complete. Corrected {corrected_files_count} Java files.")

if __name__ == "__main__":
    # --- IMPORTANT: The path is set to './src/main/java' as you specified ---
    # This assumes the script is run from the root of your Maven project.
    GENERATED_CODE_ROOT = "./src/main/java"
    
    # Get the absolute path for robustness, though relative path works if
    # the script is run from the project root.
    absolute_generated_code_root = os.path.abspath(GENERATED_CODE_ROOT)

    if not os.path.isdir(absolute_generated_code_root):
        print(f"Error: The specified generated code directory does not exist: {absolute_generated_code_root}")
        print("Please ensure you are running the script from your Maven project's root directory.")
    else:
        correct_java_imports(absolute_generated_code_root)