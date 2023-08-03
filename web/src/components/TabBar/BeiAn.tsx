import styled from "styled-components";

const BeiAnUIRoot = styled.div`
  width: 100%;
  font-size: 14px;
  font-weight: normal;
  line-height: 34px;
  letter-spacing: 0px;
  color: #666666;
  margin-bottom: 37px;
  text-align: center;
  padding: 0 10px;
`;
export const BeiAnUI: React.FC = (props) => {
  return (
    <BeiAnUIRoot>
      © 2022 弗络科技(苏州)有限公司.All rights reserved. ｜
      备案号：苏ICP备19106018号 ｜ 公安备案号：41230000099999
    </BeiAnUIRoot>
  );
};
