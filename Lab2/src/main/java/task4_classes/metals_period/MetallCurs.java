package task4_classes.metals_period;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Metall")
public class MetallCurs
{
    @XmlElement(name = "Record")
    private final List<PreciousMetall> m_list_metalls = new ArrayList<>();


    public MetallCurs() {}

    public List<PreciousMetall> getListMetalls() { return m_list_metalls; }
}
