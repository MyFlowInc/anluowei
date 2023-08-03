import styled from 'styled-components';
import ItemSettingContainer from '../../components/ItemSetting/ItemSettingContainer'

const FlowItemRoot = styled.div`
      height: 100%;
    width: 100%;
`
const FlowItemSetting: React.FC = () => {
  return (
    <FlowItemRoot>
      <ItemSettingContainer />
    </FlowItemRoot>
  );
};

export default FlowItemSetting;
