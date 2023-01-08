rootProject.name = "pabiBackend"

include("server")
include(
    "user",
    "user:presentation",
    "user:application",
    "user:domain",
    "user:infrastructure",
)