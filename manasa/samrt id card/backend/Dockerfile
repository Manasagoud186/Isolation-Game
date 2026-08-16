FROM node:18
WORKDIR /app

# Install deps (including dev deps for ts-node-dev)
COPY package.json package-lock.json* ./
RUN npm install

# Copy source
COPY . .

EXPOSE 3001

# Start dev server
CMD ["npm","run","dev"]
