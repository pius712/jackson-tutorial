package pius.jacksontutorial.profile

data class ProfileWithGetter(val nickname:String,
                             val bio:String,
                             val age:Int) {

    fun getGreet():String {
        return "hello"
    }
}
