package unittests;

import domainmodel.GeneIdentifier;
import domainmodel.Motif;
import domainmodel.SpeciesNomenclature;
import org.junit.Test;
import servercommunication.protocols.HTTPProtocol;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class HttpTest {


    @Test
    public void testSubmitJob() {
        //fail("Not yet implemented");
        HTTPProtocol http = new HTTPProtocol();
        Collection<GeneIdentifier> geneIDs = new ArrayList<GeneIdentifier>();
        GeneIdentifier gene = new GeneIdentifier("TP53", SpeciesNomenclature.HOMO_SAPIENS_HG19_HGNC);
        geneIDs.add(gene);
        float AUCThreshold = 1f;
        int rankThreshold = 1000;
        float NESThreshold = 2f;
        //int followingID = http.sentJob("test job", geneIDs, AUCThreshold, rankThreshold, NESThreshold, 0, 0) + 1;
        GeneIdentifier gene2 = new GeneIdentifier("CASP3", SpeciesNomenclature.HOMO_SAPIENS_HG19_HGNC);
        geneIDs.add(gene2);
        GeneIdentifier gene3 = new GeneIdentifier("CASP8", SpeciesNomenclature.HOMO_SAPIENS_HG19_HGNC);
        geneIDs.add(gene3);
        //assertEquals(followingID, http.sentJob("test job", geneIDs, AUCThreshold, rankThreshold, NESThreshold, 0, 0));
    }

    @Test
    public void testGetState() {
        HTTPProtocol http = new HTTPProtocol();
        //assertEquals(State.ERROR, http.getState(5));
        //assertEquals(State.FINISHED, http.getState(3));
        //assertEquals(State.RUNNING, http.getState(2));
        //assertEquals(State.REQUESTED, http.getState(1));
    }

    @Test
    public void testIsReQuested() {
        HTTPProtocol http = new HTTPProtocol();
        Collection<GeneIdentifier> geneIDs = new ArrayList<GeneIdentifier>();
        GeneIdentifier gene = new GeneIdentifier("TP53", SpeciesNomenclature.HOMO_SAPIENS_HG19_HGNC);
        geneIDs.add(gene);
        float AUCThreshold = 1f;
        int rankThreshold = 1000;
        float NESThreshold = 2f;
        //int jobID = http.sentJob("test job", geneIDs, AUCThreshold, rankThreshold, NESThreshold, 0,0);
        //assertEquals(State.REQUESTED, http.getState(jobID));
    }

    @Test
    public void testGetMotifs() {
        HTTPProtocol http = new HTTPProtocol();
        Collection<Motif> motifs = http.getMotifs(1);
        assertEquals(0, motifs.size());
    }

	/*
	@Test
	public void testWholeCircle(){
		HTTPService http = new HTTPService();
		Collection<GeneIdentifier> geneIDs = new ArrayList<GeneIdentifier>();
		GeneIdentifier gene = new GeneIdentifier("TP53", SpeciesNomenclature.HOMO_SAPIENS_HG19_HGNC);
		geneIDs.add(gene);
		float AUCThreshold = 1f;
		int rankThreshold = 1000;
		float NESThreshold = 2f;
		int jobID = http.sentJob(geneIDs, AUCThreshold, rankThreshold, NESThreshold);
		while (http.getState(jobID) != State.FINISHED){
			System.out.println(http.getState(jobID).toString());
		}
		Collection<Motif> motifs = http.getMotifsAndTracks(1);
		assertEquals(0, motifs.size());
		
	}
	*/


}
