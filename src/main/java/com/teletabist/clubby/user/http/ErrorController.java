@Controller
public class ClubbyErrorController implements ErrorController  {

    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error-404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}