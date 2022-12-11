import { Button, FormControl, InputAdornment, OutlinedInput } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import { useLocation } from 'react-router-dom'
import Grid2 from '@mui/material/Unstable_Grid2' // Grid2 version 2
import { makeTransaction } from './Utility2'

// import Timer from './PurchaseComponents/Timer'
// import AccountSelector from './PurchaseComponents/AccountSelector'
// import CurrencyConverter from './PurchaseComponents/CurrencyConverter'
// import { makeTransaction } from './Utility'
// import { Button } from '@mui/material'

export default function Sell() {
  // username = account username
  const { username } = useParams()
  // assetId is id of asset to sell
  const search = useLocation().search
  const assetId = new URLSearchParams(search).get('asset')

  const [assetData, setAssetData] = useState()
  const [accountData, setAccountData] = useState()
  const [quantityToSell, setQuantityToSell] = useState()
  const [currencyData, setCurrencyData] = useState()

  const accountQueryURL = 'http://localhost:8080/api/v1/accounts/' + username
  const assetQueryUrl = 'http://localhost:8080/api/v1/assets/' + assetId
  // BASED on this value, display the correct image
  const currencyURL = 'https://api.coingecko.com/api/v3/coins'

  function updateQuantity(v) {
    setQuantityToSell(v)
  }

  function setMax() {
    setQuantityToSell(assetData.quantity)
    console.log('Set max')
  }

  useEffect(() => {
    fetch(accountQueryURL)
      .then((response) => response.json())
      .then((json) => setAccountData(json))
    fetch(assetQueryUrl)
      .then((response) => response.json())
      .then((json) => {
        setAssetData(json)
        var currencyQueryURL = currencyURL + '/' + json.cryptoId
        fetch(currencyQueryURL)
          .then((response) => response.json())
          .then((json) => {
            console.log(json)
            setCurrencyData(json)
          })
      })
  }, [])

  return (
    <>
      <h1>Sell Asset</h1>
      <Grid2
        container
        spacing={4}
        direction="row"
        justify="center"
        justifyContent="center"
        alignItems="center"
        alignContent="center"
        textAlign="center"
      >
        <Grid2 h1 xs={6} textAlign="left">
          Quantity to sell
        </Grid2>
        <Grid2 h1 xs={6} align="left">
          <FormControl sx={{ width: '25ch' }}>
            {assetData && (
              <>
                <OutlinedInput
                  type="number"
                  placeholder="Enter the amount"
                  value={quantityToSell}
                  onChange={(e) => updateQuantity(e.target.value)}
                  inputProps={{ min: 0, max: assetData.quantity }}
                  endAdornment={
                    <InputAdornment position="end">
                      /{assetData && Math.round(assetData.quantity * 100) / 100}
                    </InputAdornment>
                  }
                />
                <Button onClick={() => setMax()} variant="outlined">
                  Set Max
                </Button>
              </>
            )}
          </FormControl>
        </Grid2>
        <Grid2 h1 xs={6} textAlign="left">
          Exchange Rate (USD/coin)
        </Grid2>
        <Grid2 h1 xs={6} textAlign="center">
          {currencyData && currencyData.market_data.current_price.usd}
        </Grid2>
        <Grid2 h1 xs={6} textAlign="left">
          Amount you receive:
        </Grid2>
        {currencyData && (
          <Grid2 h1 xs={6} textAlign="center">
            $ {Math.round(currencyData.market_data.current_price.usd * quantityToSell * 100) / 100}{' '}
            USD
          </Grid2>
        )}
        <Grid2 h1 xs={12} align="center">
          <Button
            variant="contained"
            onClick={() => makeTransaction(accountData, quantityToSell, assetData, currencyData)}
          >
            Make Transaction
          </Button>
        </Grid2>
      </Grid2>
    </>
  )
}
