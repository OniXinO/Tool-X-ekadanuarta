# Tool-X for Android (Termux)

This is a Tool-X installer for Android via the Termux app.

## Prerequisites

1. Install [Termux](https://f-droid.org/en/packages/com.termux/) from F-Droid
2. Give Termux storage permission if needed

## Installation

### Method 1: Direct download and install

1. Open Termux
2. Run the following commands:

```bash
# Install curl
pkg install curl -y

# Download and run the installation script
curl -O https://raw.githubusercontent.com/OniXinO/Tool-X-ekadanuarta/main/tool-x-android/install-termux.sh
chmod +x install-termux.sh
./install-termux.sh
```

### Method 2: Clone the repository

```bash
# Install git
pkg install git -y

# Clone the repository
git clone https://github.com/OniXinO/Tool-X-ekadanuarta

# Navigate to the directory and run the installer
cd Tool-X-ekadanuarta/tool-x-android
chmod +x install-termux.sh
./install-termux.sh
```

## Usage

After installation, simply type `Tool-X` in Termux to launch the program.

## Customizing Termux appearance (optional)

For better display of the Tool-X interface, it's recommended to set up a font and colors:

```bash
pkg install wget -y
wget -O ~/.termux/font.ttf https://github.com/powerline/fonts/raw/master/DejaVuSansMono/DejaVu%20Sans%20Mono%20for%20Powerline.ttf
wget -O ~/.termux/colors.properties https://raw.githubusercontent.com/Mayccoll/Gogh/master/themes/Dracula.properties
termux-reload-settings
```

## Note

Use this tool at your own risk and only for legal purposes.
