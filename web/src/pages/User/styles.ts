import styled from "styled-components";

export const LoginRoot = styled.div`
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  height: 100%;

  .login-form {
    width: 100%;
    max-width: 328px;
  }

  .ant-form-item-control-input-content {
    display: flex;
    justify-content: space-between;
  }
  .login-button {
    width: 100%;
    background: #2845d4;
  }
  .login-form-forgot {
    display: inline;
    color: #1677ff;
  }
`;

export const Container = styled.div`
  padding-top: 46px;
  padding-left: 10%;
  padding-right: 10%;
  align-items: center;
  width: 100%;
  background: #ffffff;
  display: flex;
  justify-content: center;
  flex: 1;
  overflow: overlay;
  .logo_2 {
    max-width: 40%;
    margin-right: 154px;
    flex: 1;
  }
  .triangle {
    border-width: 0px 0px 64px 64px;
  }

  @media (max-width: 800px) {
    .logo_2 {
      display: none;
    }
  }
`;

export const FormRoot = styled.div`
  flex: 1;
  height: 460px;
  min-width: 370px;
  display: flex;
  flex-direction: column;
  align-items: center;
  flex-shrink: 0;
  border-radius: 10px;
  opacity: 1;
  background: #ffffff;
  box-shadow: 0px 0px 10px 10px rgba(0,0,0,0.1);
  .login_tilte {
    margin-top: 37px;
    margin-bottom: 40px;

    font-size: 24px;
    font-weight: normal;
    line-height: 34px;
    display: flex;
    align-items: center;
    letter-spacing: 0px;
    color: #000000;
    margin-top: 37px;
  }
`;

export const RegisterRoot = styled.div`
  display: flex;
  flex-direction: column;
  background-color: #ffffff;
  height: 100%;

  .register-form {
    width: 100%;
    max-width: 328px;
  }

  .register-button {
    width: 100%;
    background: #2845d4;
  }
`;
