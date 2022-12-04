import axios from 'axios'

export async function makeTransaction(username, amount, cryptoId) {
  const accountResponse = await fetch('http://localhost:8080/api/v1/accounts/' + username)
  const accountData = await accountResponse.json()
  console.log(accountData)

  // CHECK IF THER ARE ENOUGH FUNDS
  if (amount > accountData.accountBalance) {
    return alert('Not enough funds')
  }

  var assetPrev

  // TODO: If asset with cryptoId exists, update asset quantity
  const assetsResponse = await fetch('http://localhost:8080/api/v1/assets/?username=' + username)
  const assetsList = await assetsResponse.json()
  for (var i = 0; i < assetsList.length; i++) {
    console.log(assetsList[i]['cryptoId'], cryptoId)
    if (assetsList[i]['cryptoId'] === cryptoId) {
      // Save this assetID
      console.log('found')
      assetPrev = assetsList[i]
    }
  }

  // Calculate how much quantity is given at price
  const cryptoResponse = await fetch('https://api.coingecko.com/api/v3/coins/' + cryptoId)
  const cryptoData = await cryptoResponse.json()
  var quantity = amount / cryptoData.market_data.current_price.usd

  // TODO: Subtract amount from account
  var putAccountURL = 'http://localhost:8080/api/v1/accounts/' + username
  axios
    .put(putAccountURL, {
      accountBalance: accountData.accountBalance - amount,
    })
    .then(function (response) {
      console.log(response)
    })
    .catch(function (error) {
      console.log(error)
    })

  if (assetPrev) {
    // PUT REQUEST TO UPDATE AMOUNT
    console.log('puting')
    var putAssetURL = 'http://localhost:8080/api/v1/assets/' + assetPrev['assetId']
    axios
      .put(putAssetURL, {
        quantity: quantity + assetPrev['quantity'],
      })
      .then(function (response) {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error)
      })
  } else {
    console.log('posting')
    var postAssetURL = 'http://localhost:8080/api/v1/assets'
    axios
      .post(postAssetURL, {
        account: username,
        cryptoId: cryptoId,
        cryptoName: cryptoId,
        quantity: quantity,
      })
      .then(function (response) {
        console.log(response)
      })
      .catch(function (error) {
        console.log(error)
      })
  }

  // Create transaction object
  var postTransactionURL = 'http://localhost:8080/api/v1/transactions'
  console.log('making transation object')
  axios
    .post(postTransactionURL, {
      account: username,
      cryptoId: cryptoId,
      cryptoName: cryptoId,
      exchangeRate: cryptoData.market_data.current_price.usd,
      quantityTransacted: quantity,
    })
    .then(function (response) {
      console.log(response)
    })
    .catch(function (error) {
      console.log(error)
    })
}
