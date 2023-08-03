import LogoSvg from "../../assets/menu_logo.svg";

export const MenuLogo: React.FC<any> = (props: any) => {
  const { className } = props;
  return <img src={LogoSvg} className={className} />;
};
