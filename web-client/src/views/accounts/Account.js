import React, { useState, useEffect } from 'react'
import { useParams } from 'react-router-dom'
import { Link } from 'react-router-dom'

import axios from 'axios'
import Grid2 from '@mui/material/Unstable_Grid2' // Grid2 version 2
import PropTypes from 'prop-types'
import Box from '@mui/material/Box'
import Table from '@mui/material/Table'
import TableBody from '@mui/material/TableBody'
import TableCell from '@mui/material/TableCell'
import TableContainer from '@mui/material/TableContainer'
import TableHead from '@mui/material/TableHead'
import TablePagination from '@mui/material/TablePagination'
import TableRow from '@mui/material/TableRow'
import TableSortLabel from '@mui/material/TableSortLabel'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button'

import { visuallyHidden } from '@mui/utils'

/** FOR ASSETS **/

function assetDescendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1
  }
  if (b[orderBy] > a[orderBy]) {
    return 1
  }
  return 0
}

function assetGetComparator(order, orderBy) {
  return order === 'desc'
    ? (a, b) => assetDescendingComparator(a, b, orderBy)
    : (a, b) => -assetDescendingComparator(a, b, orderBy)
}

function assetStableSort(array, comparator) {
  const stabilizedThis = array.map((el, index) => [el, index])
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0])
    if (order !== 0) {
      return order
    }
    return a[1] - b[1]
  })
  return stabilizedThis.map((el) => el[0])
}

const assetHeadCells = [
  {
    id: 'cryptoName',
    numeric: false,
    disablePadding: false,
    label: 'cryptoName',
  },
  {
    id: 'cryptoId',
    numeric: true,
    disablePadding: false,
    label: 'cryptoId',
  },
  {
    id: 'quantity',
    numeric: true,
    disablePadding: false,
    label: 'quantity',
  },
  {
    id: '',
    numeric: true,
    disablePadding: false,
    label: '',
  },
]

function EnhancedTableHead(props) {
  const { order, orderBy, onRequestSort } = props
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property)
  }

  return (
    <TableHead>
      <TableRow>
        {assetHeadCells.map((headCell) => (
          <TableCell
            key={headCell.id}
            align={headCell.numeric ? 'right' : 'left'}
            padding={headCell.disablePadding ? 'none' : 'normal'}
            sortDirection={orderBy === headCell.id ? order : false}
          >
            <TableSortLabel
              active={orderBy === headCell.id}
              direction={orderBy === headCell.id ? order : 'asc'}
              onClick={createSortHandler(headCell.id)}
            >
              <span style={{ fontWeight: 'bold' }}>{headCell.label}</span>
              {orderBy === headCell.id ? (
                <Box component="span" sx={visuallyHidden}>
                  {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                </Box>
              ) : null}
            </TableSortLabel>
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  )
}

EnhancedTableHead.propTypes = {
  onRequestSort: PropTypes.func.isRequired,
  order: PropTypes.oneOf(['asc', 'desc']).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
}

/** FOR TRANSACTIONS **/

function tDescendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1
  }
  if (b[orderBy] > a[orderBy]) {
    return 1
  }
  return 0
}

function tGetComparator(order, orderBy) {
  return order === 'desc'
    ? (a, b) => tDescendingComparator(a, b, orderBy)
    : (a, b) => -tDescendingComparator(a, b, orderBy)
}

function tStableSort(array, comparator) {
  const stabilizedThis = array.map((el, index) => [el, index])
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0])
    if (order !== 0) {
      return order
    }
    return a[1] - b[1]
  })
  return stabilizedThis.map((el) => el[0])
}

const tHeadCells = [
  {
    id: 'transaction_id',
    numeric: false,
    disablePadding: false,
    label: 'Transaction ID',
  },
  {
    id: 'cryptoId',
    numeric: true,
    disablePadding: false,
    label: 'Cryptocurrency ID',
  },
  {
    id: 'crypto_name ',
    numeric: false,
    disablePadding: false,
    label: 'Cryptocurrency Name',
  },
  {
    id: 'exchange_rate ',
    numeric: true,
    disablePadding: false,
    label: 'Exchange Rate',
  },
  {
    id: 'quantity_transacted ',
    numeric: true,
    disablePadding: false,
    label: 'Quantity Transacted',
  },
]

function TransactionEnhancedTableHead(props) {
  const { order, orderBy, onRequestSort } = props
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property)
  }

  return (
    <TableHead>
      <TableRow>
        {tHeadCells.map((headCell) => (
          <TableCell
            key={headCell.id}
            align={headCell.numeric ? 'right' : 'left'}
            padding={headCell.disablePadding ? 'none' : 'normal'}
            sortDirection={orderBy === headCell.id ? order : false}
          >
            <TableSortLabel
              active={orderBy === headCell.id}
              direction={orderBy === headCell.id ? order : 'asc'}
              onClick={createSortHandler(headCell.id)}
            >
              <span style={{ fontWeight: 'bold' }}>{headCell.label}</span>
              {orderBy === headCell.id ? (
                <Box component="span" sx={visuallyHidden}>
                  {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                </Box>
              ) : null}
            </TableSortLabel>
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  )
}

TransactionEnhancedTableHead.propTypes = {
  onRequestSort: PropTypes.func.isRequired,
  order: PropTypes.oneOf(['asc', 'desc']).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
}

export default function Accounts() {
  const [orderAssets, setOrderAssets] = useState('asc')
  const [orderByAssets, setOrderByAssets] = useState('')
  const [orderTransactions, setOrderTransactions] = useState('asc')
  const [orderByTransactions, setOrderByTransactions] = useState('')
  const [page, setPage] = useState(0)
  const [rowsPerPage, setRowsPerPage] = useState(10)
  const [assetdata, setAssetdata] = useState([])
  const [transactiondata, setTransactiondata] = useState([])
  const [accountdata, setAccountData] = useState([])

  const { username } = useParams()

  const transactionApiURL = 'http://localhost:8080/api/v1/transactions'
  var querytransactionApiURL = transactionApiURL + '?username=' + username

  const assetApiURL = 'http://localhost:8080/api/v1/assets'
  var queryassetApiURL = assetApiURL + '?username=' + username

  const queryaccountApiURL = 'http://localhost:8080/api/v1/accounts/' + username

  /* FOR ASSETS */
  const assetHandleRequestSort = (event, property) => {
    const isAsc = orderByAssets === property && orderAssets === 'asc'
    setOrderAssets(isAsc ? 'desc' : 'asc')
    setOrderByAssets(property)
  }

  const handleClick = (event, id) => {
    console.log('open')
  }

  const assetHandleChangePage = (event, newPage) => {
    setPage(newPage)
  }

  const assetHandleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10))
    setPage(0)
  }

  const emptyAssetRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - transactiondata.length) : 0

  /* FOR TRANSACTIONS */
  const tHandleRequestSort = (event, property) => {
    const isAsc = orderByTransactions === property && orderTransactions === 'asc'
    setOrderTransactions(isAsc ? 'desc' : 'asc')
    setOrderByTransactions(property)
  }

  const tHandleChangePage = (event, newPage) => {
    setPage(newPage)
  }

  const tHandleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10))
    setPage(0)
  }

  // Avoid a layout jump when reaching the last page with empty assetdata.
  const emptyTransactionRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - transactiondata.length) : 0

  useEffect(() => {
    fetch(queryassetApiURL)
      .then((response) => response.json())
      .then((json) => setAssetdata(json))
    fetch(querytransactionApiURL)
      .then((response) => response.json())
      .then((json) => setTransactiondata(json))
    fetch(queryaccountApiURL)
      .then((response) => response.json())
      .then((json) => setAccountData(json))
  }, [])

  const deleteAccount = () => {
    axios.delete('http://localhost:8080/api/v1/accounts/' + username)
  }

  return (
    <>
      <Box sx={{ width: '100%' }}>
        <Grid2 h1 xs={3} textAlign="center">
          Account Balance:
        </Grid2>
        <Grid2 h1 xs={3} textAlign="center">
          $ {accountdata && Math.round(accountdata.accountBalance * 100) / 100} USD
        </Grid2>
        <Grid2 h1 xs={6} textAlign="center">
          Account Notes:
        </Grid2>
        <Grid2 h1 xs={6} textAlign="center">
          {accountdata && accountdata.notes}
        </Grid2>
      </Box>
      <Box sx={{ width: '100%' }}>
        <Button>
          <Link to={`/accounts/update/${username}`}>Edit Account</Link>
        </Button>
        <h1>Assets</h1>
        <Button>
          <Link to={`/currency/`}>Buy</Link>
        </Button>
        <Paper sx={{ width: '100%', mb: 2 }}>
          <TableContainer>
            <Table sx={{ minWidth: 750 }} aria-labelledby="tableTitle" size="medium">
              <EnhancedTableHead
                order={orderAssets}
                orderBy={orderByAssets}
                onRequestSort={assetHandleRequestSort}
                rowCount={assetdata.length}
              />
              <TableBody>
                {/* if you don't need to support IE11, you can replace the `assetStableSort` call with:
                  cdata.slice().sort(assetGetComparator(order, orderBy)) */}
                {assetStableSort(assetdata, assetGetComparator(orderAssets, orderByAssets))
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((row, index) => {
                    const labelId = `enhanced-table-checkbox-${index}`

                    return (
                      <TableRow
                        hover
                        onClick={(event) => handleClick(event, row.id)}
                        tabIndex={-1}
                        key={row.assetId}
                      >
                        <TableCell align="left">{row.cryptoName}</TableCell>
                        <TableCell align="right">{row.cryptoId}</TableCell>
                        <TableCell component="th" id={labelId} scope="row" align="right">
                          {row.quantity}
                        </TableCell>
                        <TableCell align="center">
                          <Link to={`/accounts/account/${username}/sell?asset=${row.assetId}`}>
                            <Button>Sell</Button>
                          </Link>
                        </TableCell>
                      </TableRow>
                    )
                  })}
                {emptyAssetRows > 0 && (
                  <TableRow
                    style={{
                      height: 53 * emptyAssetRows,
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[10, 20, 50]}
            component="div"
            count={assetdata.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={assetHandleChangePage}
            onRowsPerPageChange={assetHandleChangeRowsPerPage}
          />
        </Paper>
        <h1>Transactions</h1>
        <Paper sx={{ width: '100%', mb: 2 }}>
          <TableContainer>
            <Table sx={{ minWidth: 750 }} aria-labelledby="tableTitle" size="medium">
              <TransactionEnhancedTableHead
                order={orderTransactions}
                orderBy={orderByTransactions}
                onRequestSort={tHandleRequestSort}
                rowCount={transactiondata.length}
              />
              <TableBody>
                {/* if you don't need to support IE11, you can replace the `assetStableSort` call with:
                  cdata.slice().sort(assetGetComparator(order, orderBy)) */}
                {tStableSort(
                  transactiondata,
                  tGetComparator(orderTransactions, orderByTransactions),
                )
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((row, index) => {
                    const labelId = `enhanced-table-checkbox-${index}`

                    return (
                      <TableRow
                        hover
                        onClick={(event) => handleClick(event, row.id)}
                        tabIndex={-1}
                        key={row.transactionId}
                      >
                        <TableCell align="left">{row.transactionId}</TableCell>
                        <TableCell align="right">{row.cryptoId}</TableCell>
                        <TableCell align="right">{row.cryptoName}</TableCell>
                        <TableCell align="right">{row.exchangeRate}</TableCell>
                        <TableCell align="right">{row.quantityTransacted}</TableCell>
                        <TableCell component="th" id={labelId} scope="row" align="right">
                          {row.quantity}
                        </TableCell>
                      </TableRow>
                    )
                  })}
                {emptyTransactionRows > 0 && (
                  <TableRow
                    style={{
                      height: 53 * emptyTransactionRows,
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[10, 20, 50]}
            component="div"
            count={transactiondata.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={tHandleChangePage}
            onRowsPerPageChange={tHandleChangeRowsPerPage}
          />
        </Paper>
        <Button onClick={deleteAccount} variant="contained" color="error">
          Delete Account
        </Button>
      </Box>
    </>
  )
}
