#!/bin/bash
# Script para construir y publicar imagen Docker

set -e  # Salir si algún comando falla

IMAGE_NAME="ancb03/franchise-api" #TODO CHANGE BY YOUR DOCKER USERNAME
TAG=${1:-latest}
FULL_IMAGE_NAME="$IMAGE_NAME:$TAG"

echo "🐳 Construyendo imagen $FULL_IMAGE_NAME..."
docker build -t $FULL_IMAGE_NAME .

echo "🔐 Verificando login en DockerHub..."
if ! docker info | grep -q "Username"; then
    echo "Logueando en DockerHub..."
    docker login
fi

echo "📤 Publicando imagen $FULL_IMAGE_NAME..."
docker push $FULL_IMAGE_NAME

echo "✅ ¡Imagen publicada exitosamente!"
echo "Para usar: docker pull $FULL_IMAGE_NAME"
echo "Para ejecutar: docker run -p 8080:8080 $FULL_IMAGE_NAME"