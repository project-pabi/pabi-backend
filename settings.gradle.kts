rootProject.name = "pabi-backend"

include("server")
include("common")
include(
    "user",
    "user:presentation",
    "user:application",
    "user:domain",
    "user:infrastructure",
)
