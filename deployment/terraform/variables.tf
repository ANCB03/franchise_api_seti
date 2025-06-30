variable "aws_region" {
  default = "us-east-2"
}

variable "ami_id" {
  description = "Ubuntu AMI ID compatible con EC2 en tu región"
}

variable "instance_type" {
  default = "t2.micro"
}

variable "ssh_public_key_path" {
  description = "Ruta a tu llave SSH pública"
}

variable "dockerhub_image" {
  description = "Imagen de DockerHub, ej: usuario/franchise-api:latest"
}

variable "mongodb_uri" {
  description = "URI de conexión a MongoDB Atlas"
  sensitive   = true
}