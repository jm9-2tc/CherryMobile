SELECT
    contract.id,
    /*(contract_price
         + IIF(sms_sent > sms_count_in_price, (sms_sent - sms_count_in_price) * sms_above_contract_price, 0)
         + IIF(phone_minutes_used > phone_minutes_in_price, (phone_minutes_used - phone_minutes_in_price) * post_paid_prices.phone_minute_above_contract_price, 0)
         + IIF(internet_megabytes_used > internet_megabytes_in_price, (internet_megabytes_used - internet_megabytes_in_price) * internet_megabyte_above_contract_price, 0)
    ) AS price*/
    contract_price,
    IIF(sms_sent > sms_count_in_price, (sms_sent - sms_count_in_price) * sms_above_contract_price, 0) AS sms_price,
    IIF(phone_minutes_used > phone_minutes_in_price, (phone_minutes_used - phone_minutes_in_price) * post_paid_prices.phone_minute_above_contract_price, 0) AS phone_price,
    IIF(internet_megabytes_used > internet_megabytes_in_price, (internet_megabytes_used - internet_megabytes_in_price) * internet_megabyte_above_contract_price, 0) AS internet_price
FROM post_paid_prices
JOIN post_paid_contract ON post_paid_prices.id = post_paid_contract.contract_ptr_id
JOIN contract ON post_paid_contract.contract_ptr_id = contract.id
WHERE customer_id = CUSTOMER_ID;