--  Author: Valerio Desiati
--  Date: 2022 May 09

local typedefs = require "kong.db.schema.typedefs"

local PLUGIN_NAME = "checkemail"
local schema = {
	name = PLUGIN_NAME,
	
	fields = {
		{ consumer = typedefs.no_consumer },
		{ protocols = typedefs.protocols_http },
		{ 
			config = {
				type = "record",
				fields = {
					{
						request_header = typedefs.header_name {
							required = true,
							default = "Hello-World"
						}
					},

					{
						response_header = typedefs.header_name {
							required = true,
							default = "Bye-World" 
						}
					},
					
					{
						ttl = {
							type = "integer",
							default = 600,
							required = true,
							gt = 0,
						}
					},

					{
						auth_header_name = {
							type = "string",
							default = "authorization"
						}
					},
				},

				entity_checks = {
					{ at_least_one_of = { "request_header", "response_header" }, },
					{ distinct = { "request_header", "response_header" } },
				},
			},
		},
	},
}

return schema
