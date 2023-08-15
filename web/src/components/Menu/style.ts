import styled from 'styled-components'
interface MenuRootProps {
  display: 'block' | 'none'
}
export const MenuRoot = styled.div<MenuRootProps>`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  transition: transform;
  transition-duration: 0.5s;
  flex-flow: column;

  /* @media (max-width: 720px) {
    position: absolute;
    transform: translate(
      ${(props) => (props.display === 'none' ? '-100%' : '0')},
      0
    );
  } */
  .menu-header {
    display: flex;
    flex-direction: column;
    /* border-bottom: 1px solid var(--ion-color-step-150, #d7d8da); */
  }
  .menu-content {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    flex: 1;
    overflow: hidden;
    .menu-content-list {
      flex: 1;
      overflow: overlay;
      display: flex;
      flex-direction: column;
    }
  }
 
`
