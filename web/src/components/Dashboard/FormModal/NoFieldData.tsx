import { SmileOutlined } from "@ant-design/icons";
import { Button, Result } from "antd";

 
export const NoFieldData: React.FC = (props) => {
    return (
        <Result
            icon={<SmileOutlined />}
            title="您未拥有字段，请点击添加~"
        />
    )
}
