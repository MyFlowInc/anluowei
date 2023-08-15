import React from "react";
import styled from "styled-components";
import { Avatar } from "antd";
import { useAppSelector } from "../../store/hooks";
import { selectCollapsed } from "../../store/globalSlice";
import LogoSvg from "../../assets/menu_logo_32.png";

interface LogoTagProps {
  collapsed: boolean;
}

const LogoTag = styled.div<LogoTagProps>`
  position: relative;
  display: flex;
  justify-content: ${(props) => (props.collapsed ? "center" : "start")};
  align-items: center;
  height: 64px;
  padding: 8px 16px;
  transition: padding 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);

  .logo-text {
    position: absolute;
    left: 64px;
    display: ${(props) => (props.collapsed ? "none" : "block")};
    font-size: 24px;
    font-weight: 600;
    letter-spacing: 3px;
    white-space: nowrap;
    color: #2b6aa9;

    animation-name: animationLogo;
    animation-duration: 3s;
    animation-iteration-count: infinite;
    animation-timing-function: ease-in;

    @keyframes animationLogo {
      0% {
        opacity: 0.6;
      }
      15% {
        opacity: 0.7;
      }
      30% {
        opacity: 0.8;
      }
      45% {
        opacity: 0.9;
      }
      50% {
        opacity: 1;
      }
      65% {
        opacity: 0.9;
      }
      80% {
        opacity: 0.8;
      }
      95% {
        opacity: 0.7;
      }
      100% {
        opacity: 0.6;
      }
    }
  }
`;

const HiFlowPic: React.FC = () => {
  const collapsed = useAppSelector(selectCollapsed);
  return (
    <LogoTag collapsed={collapsed}>
      <Avatar shape="square" size="large" src={LogoSvg} />
      <div className="logo-text">安酷智芯</div>
    </LogoTag>
  );
};

export default HiFlowPic;
