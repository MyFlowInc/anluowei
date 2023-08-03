import { IonApp, IonRouterOutlet, setupIonicReact } from "@ionic/react";
import { IonReactRouter } from "@ionic/react-router";
import { Redirect, Route } from "react-router-dom";
import React from "react";

/** all page */
import MyTest from "./pages/Test/MyTest";
import Login from "./pages/User/Login";
import DashboardRouterOutlet from "./routes/DashboardRouterOutlet";
import Register from "./pages/User/Register";
import Preview from "./pages/Preview";

/* Core CSS required for Ionic components to work properly */
// import "@ionic/react/css/core.css";

/* Basic CSS for apps built with Ionic */
// import '@ionic/react/css/normalize.css'
// import '@ionic/react/css/structure.css'
// import '@ionic/react/css/typography.css'

/* Optional CSS utils that can be commented out */
// import '@ionic/react/css/padding.css'
// import '@ionic/react/css/float-elements.css'
// import '@ionic/react/css/text-alignment.css'
// import '@ionic/react/css/text-transformation.css'
// import '@ionic/react/css/flex-utils.css'
// import '@ionic/react/css/display.css'

/* Theme variables */
// import "./theme/variables.css";

import "./styles/tailwind.css";
import "./styles/cover.css";

import "antd/dist/reset.css";
import { ConfigProvider } from "antd";
import zhCN from "antd/locale/zh_CN";
import PaySuccess from "./pages/PaySuccess";

setupIonicReact();

const App: React.FC = () => {
  return (
    <IonApp>
      <ConfigProvider locale={zhCN}>
        <IonReactRouter>
          <IonRouterOutlet>
            <Route exact path="/">
              <Redirect to="/dashboard" />
            </Route>
            <Route path="/dashboard">
              <DashboardRouterOutlet />
            </Route>

            <Route path="/test" exact={true}>
              <MyTest />
            </Route>
            <Route path="/preview" exact={true}>
              <Preview />
            </Route>
            <Route path="/pay-success" exact={true}>
              <PaySuccess />
            </Route>
            <Route path="/login" exact={true}>
              <Login />
            </Route>
            <Route path="/register" exact={true}>
              <Register />
            </Route>
          </IonRouterOutlet>
        </IonReactRouter>
      </ConfigProvider>
    </IonApp>
  );
};
export default App;
