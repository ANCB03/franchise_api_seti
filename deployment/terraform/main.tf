provider "aws" {
  region = var.aws_region
}

resource "aws_key_pair" "deployer" {
  key_name   = "deployer-key"
  public_key = file(var.ssh_public_key_path)
}

resource "aws_instance" "app_instance" {
  ami                    = var.ami_id
  instance_type          = var.instance_type
  key_name               = aws_key_pair.deployer.key_name
  associate_public_ip_address = true

  user_data = <<-EOF
              #!/bin/bash
              sudo apt update
              sudo apt install -y docker.io
              sudo systemctl start docker
              sudo docker run -d -p 80:8080 --name franchise-api \\
                -e MONGODB_URI=${var.mongodb_uri} \\
                ${var.dockerhub_image}
              EOF

  tags = {
    Name = "FranchiseApiServer"
  }
}