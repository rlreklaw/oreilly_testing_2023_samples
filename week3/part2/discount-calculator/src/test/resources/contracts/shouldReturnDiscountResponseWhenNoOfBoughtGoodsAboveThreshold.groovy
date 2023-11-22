package contracts

import org.springframework.cloud.contract.spec.Contract
Contract.make {
	request {
		method('POST')
		url('/discount')
		headers {
			contentType(applicationJson())
		}
		body([
				"name" : "john",
				"numberOfBoughtGoods": 5,
				"occupation" : "EMPLOYED"
		])
	}
	response {
		status(200)
		headers {
			contentType(applicationJson())
		}
		body([
				"personName": "john",
				"discountRate": 10
		])
	}
}
