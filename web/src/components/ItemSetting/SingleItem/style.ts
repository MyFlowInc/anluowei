import styled from "styled-components";

export const SingleItemContainer = styled.div`
  width: 284px; /* 计算值 */
  cursor: default;
  .single-item {
    display: flex;
    align-items: center;

    .item-title {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 120px;
      min-width: 120px;
      max-width: 120px;
      overflow: hidden;
      height: 36px;
      border-radius: 4px;
      opacity: 1;
      background-color: #ffd66b;
      color: #ffffff;
    }
    .operation-container {
      margin-left: 16px;
      width: fit-content;
      height: 34px;
      border-radius: 4px;
      opacity: 1;
      background: #ffffff;
      box-shadow: 0px 4px 10px 0px rgba(0, 0, 0, 0.1);
      display: flex;
      align-items: center;
      img{
          width: 16px;
          height: 16px;
          margin: auto 8px;
      }
    }
  }
  .line-container {
    display: flex;
  }
`;
