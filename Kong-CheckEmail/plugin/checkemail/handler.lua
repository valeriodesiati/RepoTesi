--  Author: Valerio Desiati
--  Date: 2022 May 09
--  File: handler.lua

local checkemail = {
	PRIORITY = 1003,
	VERSION = "1.0"
}

-- local kong = require 'kong'
local kong = kong
local cjson_safe = require 'cjson.safe'
local basexx = require 'basexx'
local http = require 'socket.http'

function checkemail:access(conf)

	local function SplitToken(token)
		local segments = {}
		for str in string.gmatch(token, "([^\\.]+)") do
			table.insert(segments, str)
		end
		return segments
	end

	local function ParseToken(token)
		local segments = SplitToken(token)
		if #segments ~= 3 then
			return nil, nil, nil, "Invalid token"
		end

		local header, err = cjson_safe.decode(basexx.from_url64(segments[1]))
		if err then
			return nil, nil, nil, "Invalid header"
		end

		local body, err = cjson_safe.decode(basexx.from_url64(segments[2]))
		if err then
			return nil, nil, nil, "Invalid body"
		end

		local sig, err = basexx.from_url64(segments[3])
		if err then
			return nil, nil, nil, "Invalid signature"
		end

		return header, body, sig
	end


	local header = kong.request.get_header(conf.auth_header_name)

	local headerTok, bodyTok, sigTok = ParseToken(string.gsub(header, "Authorization: Bearer ", "", 1))

	if bodyTok then
		return kong.response.error(403)
	end

	local body, code, headers, status = http.request("http://restservice-springid.azurewebsites.net/join/checkemail/"..bodyTok.email)

	if code == 200 then
		return kong.response.exit(200, "Success")
	end

	if code == 402 then
		return kong.response.error(402, "Payment Required")
	end

	if code == 500 then
		return kong.response.error(500, "Internal Server Error")
	end

	if code == 503 then
		return kong.response.error(503, "Service unavailable")
	end

end

return checkemail
