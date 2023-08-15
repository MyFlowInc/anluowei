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
     由MyFlow驱动 © 2023 弗络科技(苏州)有限公司
    </BeiAnUIRoot>
  );
};
