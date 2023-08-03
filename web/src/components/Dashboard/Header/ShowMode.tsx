import styled from 'styled-components'
import AnimateRadio from '../../../BaseUI/AnimateRadio'
 
const UiRoot = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  /* margin: 8px 0px; */
  // cover
  .ant-radio-button-wrapper-checked {
    background: #2845d4 !important;
  }
`

interface ShowModeProps {
  className: string
}
const ShowMode: React.FC<ShowModeProps> = (props) => {
  const { className } = props
  return (
    <UiRoot className={className}>
      <AnimateRadio />
    </UiRoot>
  )
}

export default ShowMode
