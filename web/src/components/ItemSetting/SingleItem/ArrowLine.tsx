import styled from "styled-components";

interface ArrowLineRootProps {
  rotate?: string;
}
const ArrowLineRoot = styled.div<ArrowLineRootProps>`
  font-size: 0px;
  height: 24px;
  width: 120px;
  margin: 12px 0px;
  display: flex;
  align-items: center;
  justify-content: center;
  transform: ${props => props.rotate ? props.rotate : 'unset'};

`;

export function ArrowLine(props: any) {
  const {rotate=''} = props;
  return (
    <ArrowLineRoot rotate={rotate}>
      <svg
        fill="none"
        version="1.1"
        width="10"
        height="14"
        viewBox="0 0 10 14"
      >
        <g>
          <path
            d="M6,8L10,8L5,14L0,8L4,8L4,0L6,0L6,8Z"
            fill="#B3BED3"
            fillOpacity="1"
          />
        </g>
      </svg>
    </ArrowLineRoot>
  );
}
