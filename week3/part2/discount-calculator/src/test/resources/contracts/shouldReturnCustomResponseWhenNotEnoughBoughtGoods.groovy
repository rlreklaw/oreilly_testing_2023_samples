package contracts

import  org.springframework.cloud.contract.spec.Contract

Contract.make {
	request {
		method(POST())
		url('/discount')
		headers {
			contentType(applicationJson())
		}
		body([
				"name" : "mary",
				"numberOfBoughtGoods": 0,
				"occupation" : "EMPLOYED"
		])
	}
	response {
		status(400)
		headers {
			contentType(applicationJson())
		}
		body([
				"person" : [
						"name" : "mary",
						"numberOfBoughtGoods": 0,
						"occupation" : "EMPLOYED"
				],
				 "additionalMessage": "We can't apply discounts to people who didn't buy any goods"
		])
	}
}
