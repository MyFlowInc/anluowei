import styled from 'styled-components'
import { Menu, Tooltip } from 'antd'
import pkgJSON from '../../../package.json'
import { MenuLogo } from '../Welcome/LogoIcon'
import { useEffect } from 'react'
import { useAppSelector } from '../../store/hooks'
import { selectCollapsed } from '../../store/globalSlice'
const LogoRoot = styled.div`
  display: flex;
  align-items: center;
  .hiflow {
    display: flex;
    width: 109px;
    font-size: 28px;
    font-weight: bold;
    letter-spacing: 0em;
    color: #3b4faf;
  }
  .ant-menu-item:hover {
    background-color: unset !important;
  }
  .menu-logo {
    margin-left: 2px !important;
    width: 43px;
    height: 39px;
  }
  // 覆盖样式
  .ant-menu-item {
    padding-left: unset !important;
    user-select: none !important;
  }
`

const HiFlowPic: React.FC<{ rootStyle?: any }> = ({ rootStyle }) => {
  const collapsed = useAppSelector(selectCollapsed)

  useEffect(() => {
    console.log('version:' + pkgJSON.version)
  }, [])

  const item = {
    key: 'hiflowLogo',
    icon: <MenuLogo className={'menu-logo'} />,
    label: !collapsed ? (
      <LogoRoot>
        <div className="hiflow">安酷智芯</div>
      </LogoRoot>
    ) : null,
  }
  return (
    <LogoRoot style={rootStyle}>
      <Menu mode="inline" selectable={false} items={[item]} />
    </LogoRoot>
  )
}

export default HiFlowPic
