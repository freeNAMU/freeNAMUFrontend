module.exports = {
    devServer: {
        proxy: {
            "^/document": {
                target: "http://localhost:7777",
                logLevel: "debug"
            }
        }
    }
}
