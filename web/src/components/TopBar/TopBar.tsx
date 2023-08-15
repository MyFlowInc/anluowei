import styled from "styled-components";
import { LogoUI } from "./Logo";

const TopBarUIRoot = styled.div`
  display: flex;
  height: 60px;
  width: 100%;
  background: #ffffff;
  flex-shrink: 0;
  border-bottom: 1px solid #dddddd;
  .topbar-title {
    display: flex;
    align-items: center;
    margin-left: 8px;
    font-family: "Microsoft Yahei", SimHei, system-ui, sans-serif;
    font-size: 28px;
    letter-spacing: 2px;
    font-weight: 600;
    color: #2b6aa9;
  }
`;
export const TopBarUI: React.FC = (props) => {
  return (
    <TopBarUIRoot>
      <LogoUI />
      <div className="topbar-title">人事招聘系统</div>
    </TopBarUIRoot>
  );
};
