package com.pabi.user.presentation

import com.pabi.common.response.Token
import com.pabi.user.domain.dto.TokenDto
import io.swagger.annotations.ApiModelProperty

class TokenReissueDto {
    data class TokenReissueResponse(
        @ApiModelProperty(value = "토큰", required = true)
        val token: Token,
    ) {

        constructor(
            tokenReissueInfo: TokenDto.TokenReissueInfo,
        ) : this(
            token = tokenReissueInfo.token
        )
    }
}
