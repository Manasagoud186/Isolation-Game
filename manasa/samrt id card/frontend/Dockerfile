FROM node:18
WORKDIR /app

COPY package.json package-lock.json* ./
RUN npm install

COPY . .

EXPOSE 5173

# Run Vite dev server on 0.0.0.0 so it's reachable from host
CMD ["npm","run","dev","--","--host","0.0.0.0"]
