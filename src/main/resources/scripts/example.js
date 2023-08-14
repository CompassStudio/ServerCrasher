self = register({
    name: "Example",
    author: "H3xadecimal",
    version: "1.0.0"
})

self.listen("startup", function() {
    logger.info("Hello world!")
})
