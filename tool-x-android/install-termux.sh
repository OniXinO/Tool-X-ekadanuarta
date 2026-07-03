#!/data/data/com.termux/files/usr/bin/bash

echo "╔════════════════════════════════════════╗"
echo "║       Tool-X Installer for Termux      ║"
echo "╚════════════════════════════════════════╝"

# Check if running in Termux
if [ ! -d "/data/data/com.termux/files/usr" ]; then
    echo "Error: This script must be run in Termux on Android!"
    exit 1
fi

echo "[*] Updating package repositories..."
pkg update -y

echo "[*] Installing dependencies..."
pkg install -y git python python3 python3-pip

echo "[*] Cloning Tool-X repository..."
if [ -d "$HOME/Tool-X" ]; then
    echo "[!] Tool-X directory already exists. Removing it..."
    rm -rf "$HOME/Tool-X"
fi

git clone https://github.com/OniXinO/Tool-X-ekadanuarta "$HOME/Tool-X"

echo "[*] Installing Tool-X..."
cd "$HOME/Tool-X"
chmod +x install
./install

echo "╔════════════════════════════════════════╗"
echo "║     Installation completed!            ║"
echo "║     Run 'Tool-X' to start the tool     ║"
echo "╚════════════════════════════════════════╝"
