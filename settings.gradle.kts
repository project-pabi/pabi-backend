rootProject.name = "pabi-backend"

include("server")
include(
    "user",
    "user:presentation",
    "user:application",
    "user:domain",
    "user:infrastructure",
)