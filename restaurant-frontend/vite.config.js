import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import mkcert from 'vite-plugin-mkcert'

// import dotenv from 'dotenv'
// dotenv.config();
//
// const google_client = process.env.GOOGLE_CLIENT_ID;

// https://vite.dev/config/
export default defineConfig({
  plugins: [react(), mkcert()],
  // define: {
  //   __GOOGLE_CLIENT_ID__: JSON.stringify(google_client),
  // },
  server: {
    https: {},    // IMPORTANT
    port: 3001,
  },

})
