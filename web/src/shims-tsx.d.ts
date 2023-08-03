import Vue, { VNode } from 'vue';

declare global {
  namespace JSX {
  }
  interface Window {
    ws: null | WebSocket;
  }
}

 