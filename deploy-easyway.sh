#!/bin/bash

# Default values
USER=""
HOST=""
SSH_KEY=""

# Parse named arguments
while [[ "$#" -gt 0 ]]; do
    case $1 in
        -u|--user) USER="$2"; shift ;;
        -h|--host) HOST="$2"; shift ;;
        -k|--key) SSH_KEY="$2"; shift ;;
        *) echo "Unknown parameter passed"; exit 1 ;;
    esac
    shift
done

# Check if required arguments are provided
if [ -z "$USER" ] || [ -z "$HOST" ] || [ -z "$SSH_KEY" ]; then
    echo "Usage: $0 -u <username> -h <host> -k <ssh_key>"
    exit 1
fi

# SSH into the server and execute Docker commands
ssh -i "$SSH_KEY" -o StrictHostKeyChecking=no -p "22" "$USER"@"$HOST" << EOF
    echo "Logged in to $HOST"
    sudo su
    cd /home/ubuntu
    ls -ltr
    aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 267932851334.dkr.ecr.ap-south-1.amazonaws.com
    sudo docker ps -aq | xargs sudo docker stop || true
    sudo docker ps -aq | xargs sudo docker rm || true
    sudo docker rmi -f \$(sudo docker images -a -q) || true
    sudo docker compose up -d

EOF
