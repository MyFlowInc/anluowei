import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { RootState } from '.'
import { userProfile } from '../api/user'
import { userGradeList } from '../api/shop'
import _ from 'lodash'

export interface User {
  id: string;
  username: string;
  nickname: string;
  avatar: string;
  phone: string;
  gradeId: string;
  gradeName: string;
  endTime: string;
  roles: [];
}

export interface globalState {
  user: User
  gradeList:any[]
  Authorization: string
  'Authorization-key': string
  collapsed: boolean // side menu
  is_show_tour: boolean // 导航
}

const initialState: globalState = {
  user: {} as User,
  gradeList:[],
  Authorization: '',
  'Authorization-key': '',
  is_show_tour: false,
  collapsed: false,
}
// 检查用户信息

export const freshUser = createAsyncThunk('global/freshUser', async () => {
  const res = await userProfile()
  // The value we return becomes the `fulfilled` action payload
  return res.data
})

export const freshGradeList = createAsyncThunk('global/freshGradeList', async () => {
  const res = await userGradeList()
  console.log(111 ,res)
  // The value we return becomes the `fulfilled` action payload
  return _.get(res, 'data.record') || []
})


export const globalSlice = createSlice({
  name: 'global',
  initialState,
  reducers: {
    setUser: (state, action) => {
      // console.log('setUser', action)
      state.user = action.payload
    },
    loginSuccess: (state, action) => {
      console.log('loginSussess', action)
      state.user = action.payload
    },
    setIsShowTour: (state, action) => {
      // console.log('setIsShowTour', action)
      state.is_show_tour = action.payload
    },
    setCollapsed: (state, action) => {
      console.log('setCollapsed', action)
      state.collapsed = action.payload
    },
  },
  extraReducers: (builder) => {
    builder.addCase(freshUser.fulfilled, (state, action) => {
      state.user = action.payload
    })
    builder.addCase(freshGradeList.fulfilled, (state, action) => {
      state.gradeList = action.payload
    })
 
  },
})

export const { setUser, loginSuccess, setCollapsed, setIsShowTour } =
  globalSlice.actions

export const selectUser = (state: RootState) => state.global.user
export const selectCollapsed = (state: RootState) => state.global.collapsed
export const selectIsShowTour = (state: RootState) => state.global.is_show_tour
export const selectGradeList = (state: RootState) => state.global.gradeList

export default globalSlice.reducer
