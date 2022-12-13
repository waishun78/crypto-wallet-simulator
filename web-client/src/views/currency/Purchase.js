import React, { useState } from 'react'
import { Link, useParams } from 'react-router-dom'

import Timer from './PurchaseComponents/Timer'
import AccountSelector from './PurchaseComponents/AccountSelector'
// import CurrencySelector from './PurchaseComponents/CurrencySelector'
import CurrencyConverter from './PurchaseComponents/CurrencyConverter'
import { makeTransaction } from './Utility'
import { Button } from '@mui/material'

export default function Purchase() {
  const { id } = useParams()
  const [accountChoice, setAccountChoice] = useState('')
  const [amount, setAmount] = useState(0)

  const updateAccount = (accountId) => {
    setAccountChoice(accountId)
  }

  const updateParentAmount = (amount) => {
    setAmount(amount)
  }

  // TODO: Pass account, currency information through submit into a separate function to launch transaction

  return (
    <>
      <h1>Make Transaction</h1>
      <AccountSelector updateAccount={updateAccount} />
      <Timer />
      {/* <CurrencySelector id={currencyId} updateCurrencyFunction={updateCurrencyIdFromChild} /> */}
      <CurrencyConverter id={id} updateParentAmount={updateParentAmount} />
      {/* {accountChoice} */}
      <Button onClick={() => makeTransaction(accountChoice, amount, id)}>
        <Link to={`/transactions`}>Submit</Link>
      </Button>
    </>
  )
}
