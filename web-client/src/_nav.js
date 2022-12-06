import React from 'react'
import CIcon from '@coreui/icons-react'
import {
  cilBell,
  cilCalculator,
  cilChartPie,
  // cilCursor,
  // cilDescription,
  cilDrop,
  cilNotes,
  // cilPencil,
  // cilPuzzle,
  // cilSpeedometer,
  cilStar,
  // cibQuantopian,
} from '@coreui/icons'
import { CNavGroup, CNavItem, CNavTitle } from '@coreui/react'

const _nav = [
  // {
  //   component: CNavItem,
  //   name: 'Dashboard',
  //   to: '/dashboard',
  //   icon: <CIcon icon={cilSpeedometer} customClassName="nav-icon" />,
  // },
  {
    component: CNavTitle,
    name: 'Tabs',
  },
  {
    component: CNavItem,
    name: 'Currency',
    to: '/currency',
    icon: <CIcon icon={cilDrop} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: 'Forms',
    icon: <CIcon icon={cilNotes} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Accounts',
    to: '/accounts',
    icon: <CIcon icon={cilChartPie} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: 'Icons',
    icon: <CIcon icon={cilStar} customClassName="nav-icon" />,
  },
  {
    component: CNavGroup,
    name: 'Notifications',
    icon: <CIcon icon={cilBell} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Transactions',
    to: '/transactions',
    icon: <CIcon icon={cilCalculator} customClassName="nav-icon" />,
  },
  // {
  //   component: CNavItem,
  //   name: 'Portfolios',
  //   to: '/portfolios',
  //   icon: <CIcon icon={cibQuantopian} customClassName="nav-icon" />,
  // },
  // {
  //   component: CNavTitle,
  //   name: 'Extras',
  // },
  // {
  //   component: CNavItem,
  //   name: 'Docs',
  //   href: 'https://coreui.io/react/docs/templates/installation/',
  //   icon: <CIcon icon={cilDescription} customClassName="nav-icon" />,
  // },
]

export default _nav
