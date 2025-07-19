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
        *) echo "Unknown parameter passed: $1"; exit 1 ;;
    esac
    shift
done

# Validate required parameters
if [ -z "$USER" ] || [ -z "$HOST" ] || [ -z "$SSH_KEY" ]; then
    echo "Usage: $0 -u <username> -h <host> -k <ssh_key>"
    exit 1
fi

# SSH and run deployment commands
ssh -i "$SSH_KEY" -o StrictHostKeyChecking=no "$USER@$HOST" << EOF
    echo "✅ Logged in to $HOST"
    cd /home/ubuntu || exit 1

    echo "🧼 Cleaning up old Docker containers/images..."
    docker ps -aq | xargs -r docker stop
    docker ps -aq | xargs -r docker rm
    docker images -q | xargs -r docker rmi -f

    echo "🔐 Logging into AWS ECR..."
    aws ecr get-login-password --region ap-south-1 | docker login --username AWS --password-stdin 566619001659.dkr.ecr.ap-south-1.amazonaws.com

    echo "🚀 Pulling latest image from ECR..."
    docker pull 566619001659.dkr.ecr.ap-south-1.amazonaws.com/easywaybe:latest

    echo "📦 Starting application with Docker Compose..."
    docker compose down || true
    docker compose up -d

    echo "✅ Deployment completed successfully."
EOF
