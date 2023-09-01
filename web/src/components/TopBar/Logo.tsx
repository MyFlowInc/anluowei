import styled from 'styled-components'
import logoSvg from '../../assets/anku_logo.png'
import logoLoginSvg from '../../assets/logo_login.svg'

export const Logo: React.FC<any> = (props: any) => {
  const { className } = props
  return <img src={logoSvg} className={className} />
}

const LogoUIRoot = styled.div`
  display: flex;
  align-items: center;
  padding: 4px 0px;
  .logo_img {
    /* margin-left: 44px; */
    max-height: 100%;
    height: 100%;
    width: auto;
  }
  .title {
    margin-left: 16px;
    /* HIFLOW平台 */
    font-size: 20px;
    font-weight: normal;
    line-height: 34px;
    display: flex;
    align-items: center;
    letter-spacing: 0px;
    color: #343434;
  }
`
export const LogoUI: React.FC = (props) => {
  return (
    <LogoUIRoot>
      <Logo className="logo_img md:ml-8" />
    </LogoUIRoot>
  )
}

export const LogoUI2: React.FC<any> = (props: any) => {
  const { className } = props
  return <img src={logoLoginSvg} className={className} />
}
