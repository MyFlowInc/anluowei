import styled from "styled-components";

export const LoadingRoot = styled.div`
  display: flex;
  width: 100vw;
  height: 100vh;
  align-items: center;
  justify-content: center;
`;

interface RouterContainerProps {
  display: "block" | "none";
}

export const RouterContainer = styled.div<RouterContainerProps>`
  display: flex;
  flex-direction: row;
  height: 100%;
  position: relative;
  overflow: auto;
  overflow-x: hidden;
  transition: all;
  transition-duration: 0.5s;
  background: #edeff3;

  .router-content {
    flex: 1;
    position: relative;
    overflow: auto;
    transition: all;
    transition-duration: 0.5s;
    background: #edeff3;
  }

  .flow-sider {
    overflow: hidden;
    background: #edeff3;
  }
  .trigger {
    margin-left: 8px;
  }
  //cover
  .ant-menu-light {
    background: #edeff3;
  }
  .ant-menu-root.ant-menu-inline {
    border: unset !important;
  }

  @media (max-width: 720px) {
    .router-content {
      position: absolute;
      width: 100%;
      height: 100%;
      left: ${(props) => (props.display === "none" ? "0px" : "218px")};
    }
  }
`;

export const ResponsiveMenu = styled.div`
  .hide-mobile-menu {
    display: none;
  }
  .show-mobile-menu {
    display: block;
  }
`;
