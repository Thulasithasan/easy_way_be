jobs:
  deploy-to-EC2:
    needs: build-docker-image
    runs-on: ubuntu-latest

    steps:
      - name: Prepare SSH Key
        run: |
          echo "${secrets.SSH_KEY}" > easyway.pem
          chmod 400 easyway.pem

      - name: Upload deploy script to EC2
        uses: appleboy/scp-action@v0.1.4
        with:
          host: ${secrets.HOST}
          username: ${secrets.USERNAME}
          key: ${secrets.SSH_KEY}
          source: ./deploy-easyway.sh       # This is your local script path in your repo
          target: ~/deploy-easyway.sh       # Path on EC2 server

      - name: Deploy on EC2 via SSH
        uses: appleboy/ssh-action@v1
        with:
          host: ${secrets.HOST}
          username: ${secrets.USERNAME}
          key: ${secrets.SSH_KEY}
          script: |
            chmod +x ~/deploy-easyway.sh      # Make script executable on EC2
            ~/deploy-easyway.sh -u ${secrets.USERNAME} -h ${secrets.HOST} -k ./easyway.pem

      - name: Cleanup
        run: rm easyway.pem
