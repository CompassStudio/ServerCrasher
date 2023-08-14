## startup
脚本加载

## target_selected
目标已选择
- String **target** 直接输入的目标地址

## dns_resolved
地址已解析
- String **host** 目标主机
- int **port** 目标端口

## bot_configured
bot已配置
- String **name** Bot名称
- int **count** Bot数量

# pre_start
攻击线程启动前

# random_name_gen
随机名称生成时
- AtomReference<String> **name** 随机名称AtomReference实例，可更改

# bot_created
Bot构建方法完成
- String **name** 昵称
- ScriptBot **bot** 机器人实例

# bot_pre_connect
Bot连接方法开始时
- ScriptBot **bot** 机器人实例

# bot_login
Bot登入服务器后
- ScriptBot **bot** 机器人实例

# bot_login_finish
内置的Listener加载结束
- ScriptBot **bot** 机器人实例