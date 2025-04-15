# Etapa 1: build
FROM node:18 AS builder

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Etapa 2: producción
FROM node:18

WORKDIR /app

COPY package*.json ./
RUN npm install --only=production

COPY --from=builder /app/dist ./dist

EXPOSE 3000

# 👇 Este es el cambio clave
CMD ["node", "dist/server.js"]
