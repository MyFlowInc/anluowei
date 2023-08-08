import LogoSvg from "../../assets/menu_logo.png";

export const MenuLogo: React.FC<any> = (props: any) => {
  const { className } = props;
  return <img src={LogoSvg} className={className} />;
};
