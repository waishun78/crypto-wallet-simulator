import axios from 'axios'

export function makeTransaction(accountData, quantityToSell, assetData, currencyData) {
  // Sell Asset
  var putAssetURL = 'http://localhost:8080/api/v1/assets/' + assetData.assetId
  if (quantityToSell >= assetData.quantity) {
    axios
      .delete(putAssetURL)
      .then(function (response) {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error)
      })
  } else {
    axios
      .put(putAssetURL, {
        quantity: assetData.quantity - quantityToSell,
      })
      .then(function (response) {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error)
      })
  }

  // Update account balance information
  var putAccountURL = 'http://localhost:8080/api/v1/accounts/' + accountData.username
  axios
    .put(putAccountURL, {
      accountBalance:
        accountData.accountBalance + quantityToSell * currencyData.market_data.current_price.usd,
    })
    .then(function (response) {
      console.log(response)
    })
    .catch(function (error) {
      console.log(error)
    })

  // Create transaction object
  var postTransactionURL = 'http://localhost:8080/api/v1/transactions'
  console.log('making transation object')
  axios
    .post(postTransactionURL, {
      account: accountData.username,
      cryptoId: assetData.cryptoId,
      cryptoName: assetData.cryptoName,
      exchangeRate: currencyData.market_data.current_price.usd,
      quantityTransacted: -quantityToSell,
    })
    .then(function (response) {
      console.log(response)
    })
    .catch(function (error) {
      console.log(error)
    })
}
